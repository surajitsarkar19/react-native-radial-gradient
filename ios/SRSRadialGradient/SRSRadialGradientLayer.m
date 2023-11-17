#import "SRSRadialGradientLayer.h"

@implementation SRSRadialGradientLayer
{
    BOOL _needsNewGradient;
    BOOL _startCenterInitialized;
    BOOL _endCenterInitialized;
    BOOL _endRadiusInitialized;
    CGGradientRef _lastGradient;
}

- (instancetype)init
{
    self = [super init];

    if (self)
    {
        self.needsDisplayOnBoundsChange = YES;
        self.masksToBounds = YES;
        _needsNewGradient = YES;
    }

    return self;
}

- (void)setNeedsNewGradient
{
    _needsNewGradient = YES;
    [self setNeedsDisplay];
}

- (void)setColors:(NSArray<UIColor *> *)colors
{
    _colors = colors;
    [self setNeedsNewGradient];
}

- (void)setLocations:(NSArray<NSNumber *> *)locations
{
    _locations = locations;
    [self setNeedsNewGradient];
}

- (void)setStartCenter:(CGPoint)center
{
    _startCenter = center;
    _startCenterInitialized = YES;
    [self setNeedsNewGradient];
}

- (void)setStartRadius:(CGFloat)radius
{
    _startRadius = radius;
    [self setNeedsNewGradient];
}

- (void)setEndCenter:(CGPoint)center
{
    _endCenter = center;
    _endCenterInitialized = YES;
    [self setNeedsNewGradient];
}

- (void)setEndRadius:(CGFloat)radius
{
    _endRadius = radius;
    _endRadiusInitialized = YES;
    [self setNeedsNewGradient];
}

- (void)drawInContext:(CGContextRef)ctx
{
    if (!_colors)
        return;

    if (!_lastGradient || _needsNewGradient)
    {
        if (_locations) {
            CGFloat *locations = nil;

            locations = malloc(sizeof(CGFloat) * _colors.count);

            for (NSInteger i = 0; i < _colors.count; i++)
            {
                if (_locations.count > i)
                {
                    locations[i] = _locations[i].floatValue;
                }
                else
                {
                    locations[i] = (1 / (_colors.count - 1)) * i;
                }
            }
            _lastGradient = CGGradientCreateWithColors(nil, (CFArrayRef)_colors, locations);
            free(locations);
        } else {
            _lastGradient = CGGradientCreateWithColors(nil, (CFArrayRef)_colors, nil);
        }
        _needsNewGradient = NO;
    }

    CGSize size = self.bounds.size;

    if (size.width == 0.0 || size.height == 0.0)
        return;

    CGPoint defaultCenter = CGPointMake(size.width / 2, size.height / 2);

    CGContextDrawRadialGradient(ctx, _lastGradient,
                                _startCenterInitialized ? self.startCenter : defaultCenter,
                                self.startRadius,
                                _endCenterInitialized ? self.endCenter : defaultCenter,
                                _endRadiusInitialized ? self.endRadius : MIN(size.width / 2, size.height / 2),
                                kCGGradientDrawsBeforeStartLocation | kCGGradientDrawsAfterEndLocation);
}






- (void)display {
    [super display];
    
    BOOL hasAlpha = NO;

    for (NSInteger i = 0; i < self.colors.count; i++) {
        hasAlpha = hasAlpha || CGColorGetAlpha((CGColorRef)self.colors[i]) < 1.0;
    }

     UIGraphicsImageRendererFormat *format;
    if (@available(iOS 11.0, *)) {
        format = [UIGraphicsImageRendererFormat preferredFormat];
    } else {
        format = [UIGraphicsImageRendererFormat defaultFormat];
    }
    format.opaque = !hasAlpha;
    UIGraphicsImageRenderer *renderer = [[UIGraphicsImageRenderer alloc] initWithSize:self.bounds.size format:format];
    UIImage *image = [renderer imageWithActions:^(UIGraphicsImageRendererContext * _Nonnull ref) {
        [self drawInContext:ref.CGContext];
    }];
    
    self.contents = (__bridge id _Nullable)(image.CGImage);
    self.contentsScale = image.scale;

    UIGraphicsEndImageContext();
}

@end

