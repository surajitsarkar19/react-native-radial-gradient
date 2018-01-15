#import "SRSRadialGradientLayer.h"

@implementation SRSRadialGradientLayer
{
    BOOL _needsNewGradient;
    CGGradientRef _lastGradient;
}

- (instancetype)init
{
    self = [super init];

    if (self)
    {
        self.needsDisplayOnBoundsChange = YES;
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
    [self setNeedsNewGradient];
}

- (void)setEndRadius:(CGFloat)radius
{
    _endRadius = radius;
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

    CGContextDrawRadialGradient(ctx, _lastGradient,
                                self.startCenter,
                                self.startRadius,
                                self.endCenter,
                                self.endRadius,
                                kCGGradientDrawsBeforeStartLocation | kCGGradientDrawsAfterEndLocation);
}

@end

